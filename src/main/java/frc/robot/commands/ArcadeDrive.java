package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;

import java.util.function.Supplier;

public class ArcadeDrive extends Command {
    private final XRPDrivetrain drivetrain;
    private final Supplier<Double> xAxisSpeedSupplier;
    private final Supplier<Double> zAxisSpeedSupplier;

    public ArcadeDrive(XRPDrivetrain drivetrain, Supplier<Double> xAxisSpeedSupplier, Supplier<Double> zAxisSpeedSupplier) {
        this.drivetrain = drivetrain;
        this.xAxisSpeedSupplier = xAxisSpeedSupplier;
        this.zAxisSpeedSupplier = zAxisSpeedSupplier;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        drivetrain.arcadeDrive(xAxisSpeedSupplier.get(), zAxisSpeedSupplier.get());
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}